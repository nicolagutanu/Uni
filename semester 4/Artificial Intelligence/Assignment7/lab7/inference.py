import myModel
import torch as t
import numpy as np

# we load the model

filepath = "myNetwork.pt"
ann = myModel.Net(2, 120, 1)

ann.load_state_dict(t.load(filepath))
ann.eval()

# visualise the parameters for the ann (aka weights and biases)
# for name, param in ann.named_parameters():
#     if param.requires_grad:
#         print (name, param.data)


x1 =float(input("x1 = "))
x2 =float(input("x2 = "))
x = t.tensor([x1, x2])
print(ann(x).tolist())
print('correct: ', (np.sin(x1 + x2/np.pi)))