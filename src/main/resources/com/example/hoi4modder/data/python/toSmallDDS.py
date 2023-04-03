from PIL import Image
import sys


def convert(arg1, arg2):
    original_image = Image.open(arg1)
    result = original_image.resize((65, 67))
    result.save(arg2, "dds")


a1 = sys.argv[1]
a2 = sys.argv[2]

convert(a1, a2)
