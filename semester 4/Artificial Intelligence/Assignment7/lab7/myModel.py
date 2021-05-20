import torch as t
import torch.nn.functional as F

class Net(t.nn.Module):
    # the class for the network
    
    def __init__(self, input_layer_size, hidden_layer_size, output_layer_size):
        # we have two layers: a hidden one and an output one
        super(Net, self).__init__()
        self.hidden = t.nn.Linear(input_layer_size, hidden_layer_size)
        self.output = t.nn.Linear(hidden_layer_size, output_layer_size)

    def forward(self, x):
        # a function that implements the forward propagation of the signal
        # observe the relu function applied on the output of the hidden layer
        x = F.relu(self.hidden(x))
        #x = F.sigmoid(self.hidden(x))
        #x = F.hardsigmoid(self.hidden(x))
        x = self.output(x)
        return x
