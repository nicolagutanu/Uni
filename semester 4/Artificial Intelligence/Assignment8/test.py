'''
# load and show an image with Pillow
from PIL import Image
# load the image
image = Image.open('jl.jpg')
# summarize some details about the image
print(image.format)
print(image.mode)
print(image.size)
# show the image
image.show()
'''


'''
# load and display an image with Matplotlib
from matplotlib import image
from matplotlib import pyplot
# load image as pixel array
data = image.imread('jl.jpg')
# summarize shape of the pixel array
print(data.dtype)
print(data.shape)
1# display the array of pixels as an image
pyplot.imshow(data)
pyplot.show()
'''


'''
# create a thumbnail of an image
from PIL import Image
# load the image
image = Image.open('jl.jpg')
# report the size of the image
print(image.size)
image.show()
# create a thumbnail and preserve aspect ratio
image.thumbnail((100, 100))
# report the size of the thumbnail
print(image.size)
image.show()
'''