'''
    Allign Image as per the given template
'''

import cv2
import numpy as np

MAX_FEATURES = 500
GOOD_MATCH_PERCENT = 0.15


def align_image(raw_img, ref_img):
    '''
    Align an image as per the given reference image (Template)
    
    Args:
        raw_img (Image): Raw Image
        ref_img (Image): The refrence Image / Template
    
    Returns:
        [Imgae]: Aligned Image
        [Image]: Key point match Image
    '''

    # Convert images to grayscale
    raw_img_gray = cv2.cvtColor(raw_img, cv2.COLOR_BGR2GRAY)
    ref_img_gray = cv2.cvtColor(ref_img, cv2.COLOR_BGR2GRAY)
    
    # Detect ORB features and compute descriptors.
    orb = cv2.ORB_create(MAX_FEATURES)
    keypoints1, descriptors1 = orb.detectAndCompute(raw_img_gray, None)
    keypoints2, descriptors2 = orb.detectAndCompute(ref_img_gray, None)
    
    # Match features.
    matcher = cv2.DescriptorMatcher_create(cv2.DESCRIPTOR_MATCHER_BRUTEFORCE_HAMMING)
    matches = matcher.match(descriptors1, descriptors2, None)
    
    # Sort matches by score
    matches.sort(key=lambda x: x.distance, reverse=False)

    # Remove not so good matches
    n_good_matches = int(len(matches) * GOOD_MATCH_PERCENT)
    matches = matches[:n_good_matches]

    # Draw top matches
    top_matches_img = cv2.drawMatches(raw_img, keypoints1, ref_img, keypoints2, matches, None)
    #cv2.imwrite("test-images/matches.jpg", top_matches_img)
    
    # Extract location of good matches
    points1 = np.zeros((len(matches), 2), dtype=np.float32)
    points2 = np.zeros((len(matches), 2), dtype=np.float32)

    for i, match in enumerate(matches):
        points1[i, :] = keypoints1[match.queryIdx].pt
        points2[i, :] = keypoints2[match.trainIdx].pt
    
    # Find homography
    h, mask = cv2.findHomography(points1, points2, cv2.RANSAC)

    # Use homography
    height, width, channels = ref_img.shape
    aligned_img = cv2.warpPerspective(raw_img, h, (width, height))
    
    return aligned_img, top_matches_img
