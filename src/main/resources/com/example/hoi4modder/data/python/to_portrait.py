from PIL import Image
import sys

def to_portrait(arg1, arg2, arg3):
    original_image = Image.open(arg1)
    original_frame = Image.open(arg2)

    result = Image.new("RGBA", (65, 67))

    small_image = original_image.convert("RGBA")
    original_frame = original_frame.convert("RGBA")
    small_image = small_image.resize((37, 50))
    small_image = small_image.rotate(7, expand=True)
    result.paste(small_image, (4, 3))
    result.paste(original_frame.convert("RGB"), (0, 0), original_frame.convert("RGBA"))
    result.save("arg3", "dds")


num1 = sys.argv[1]
num1 = sys.argv[2]
num1 = sys.argv[3]