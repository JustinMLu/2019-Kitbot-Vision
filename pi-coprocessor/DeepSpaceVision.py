import cv2
import numpy as np
from networktables import NetworkTables

logging.basicConfig(level=logging.DEBUG)

NetworkTables.initialize(server='roborio-610-frc.local')

HSV_THRESH_LOW = np.array([0, 0, 220])
HSV_THRESH_HIGH = np.array([100, 60, 255])

CANNY_THRESH_MIN = 60
CANNY_THRESH_MAX = 180

W_TO_H_MIN = 0.10
W_TO_H_MAX = 0.60

CNT_AREA_MIN = 600
CNT_AREA_MAX = 6000

AREA_RATIO_MIN = 0.85
AREA_RATIO_MAX = 1.35

IMG_WIDTH = 0

kernel = np.ones((5, 5), np.uint8)


def is_valid_area(area):
    return CNT_AREA_MIN <= area <= CNT_AREA_MAX


def is_valid_rectangle(ratio):
    return W_TO_H_MIN <= ratio <= W_TO_H_MAX


def is_accurate_box(ratio):
    return AREA_RATIO_MIN <= ratio <= AREA_RATIO_MAX


def find_all_contours(img_raw):
    IMG_WIDTH = img_raw.shape[1]
    mask = cv2.inRange(cv2.cvtColor(img_raw, cv2.COLOR_BGR2HSV), HSV_THRESH_LOW, HSV_THRESH_HIGH)
    # mask2 = cv2.morphologyEx(mask, cv2.MORPH_OPEN, kernel)

    canny = cv2.Canny(mask, CANNY_THRESH_MIN, CANNY_THRESH_MAX)

    _, contours, _ = cv2.findContours(canny, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    return contours


def verify_contours(contours):
    top_contours = []

    for cnt in contours:
        box = cv2.minAreaRect(cnt)

        cnt_area = cv2.contourArea(cnt)
        width_height = sorted([box[1][0], box[1][1]])
        box_area = np.multiply(*width_height)

        if is_valid_area(cnt_area) and is_valid_rectangle(width_height[0] / width_height[1]):
            if is_accurate_box(box_area / cnt_area):
                x_y = box[0]
                top_contours.append([cnt, x_y, cnt_area, width_height])

    return top_contours


def draw_top_contours(top_contours):
    drawn_cnts = []

    for cnt in top_contours:
        drawn_cnts.append(cnt[0])

    return drawn_cnts


def get_target(top_contours):
    if len(top_contours) != 2:
        return None

    else:
        # Number
        center_dist = abs(top_contours[0][1][0] - top_contours[1][1][0])
        # Tuple of (x,y)
        midpoint = ((top_contours[0][1][0] + top_contours[1][1][0]) / 2.0, (top_contours[0][1][1] + top_contours[1][1][1]) / 2.0)
        # Number
        deviation = midpoint[0] - IMG_WIDTH / 2

        target_data = (midpoint, deviation, center_dist)

        return target_data


camera = cv2.VideoCapture(0)
camera.set(cv2.CAP_PROP_CONTRAST, -1)

if not camera.isOpened():
    print("Failed to open VideoCapture!")

while camera.isOpened():
    _, img = camera.read()

    raw_contours = find_all_contours(img)

    good_contours = verify_contours(raw_contours)

    target_data = get_target(good_contours)

    sd.putNumber('deviation', target_data[1])
    sd.putNumber('center_dist', target_data[2])

    drawn = draw_top_contours(good_contours)
    cv2.drawContours(img, raw_contours, -1, (0, 0, 255), 2)

    cv2.imshow('img', img)

    if cv2.waitKey(5) == 27:
        break

cv2.destroyAllWindows()
