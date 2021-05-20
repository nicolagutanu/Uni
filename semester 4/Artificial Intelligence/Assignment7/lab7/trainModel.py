import torch as t
import myModel
import createdb
import matplotlib.pyplot as plt


createdb.getRandomPoints()

data = t.load('mydataset.dat')
x = []
f = []
for d in data:
    x.append([d[0], d[1]])
    f.append([d[2]])
x = t.tensor(x)
f = t.tensor(f)

# we set up the lossFunction as the mean square error
lossFunction = t.nn.MSELoss()

# we create the ANN
#neurons are the size of the hidden layer
ann = myModel.Net(input_layer_size=2, hidden_layer_size=120, output_layer_size=1)

# we use an optimizer that implements stochastic gradient descent
# learning rate
optimizer_batch = t.optim.SGD(ann.parameters(), lr=0.001)

# we memorize the losses for some graphics
loss_list = []
avg_loss_list = []

# we set up the environment for training in batches
batch_size = 150
n_batches = int(len(data) / batch_size)
print(n_batches)

for epoch in range(12000):

    loss_sum = 0
    for batch in range(n_batches):
        # we prepare the current batch  -- please observe the slicing for tensors
        batch_x, batch_f = x[batch * batch_size:(batch + 1) * batch_size, ], f[batch * batch_size:(batch + 1) * batch_size, ]

        # we compute the output for this batch
        prediction = ann(batch_x)

        # we compute the loss for this batch
        loss = lossFunction(prediction, batch_f)

        # we save it for graphics
        loss_list.append(loss.item())
        loss_sum += loss.item()

        # we set up the gradients for the weights to zero (important in pytorch)
        optimizer_batch.zero_grad()

        # we compute automatically the variation for each weight (and bias) of the network
        loss.backward()

        # we compute the new values for the weights
        optimizer_batch.step()

    avg_loss_list.append(loss_sum / n_batches)

    if epoch % 200 == 0:
        y_pred = ann(x)
        loss = lossFunction(y_pred, f)
        print('\repoch: {}\tLoss =  {:.5f}'.format(epoch, loss.item()))

# Specify a path
filepath = "myNetwork.pt"

# save the model to file
t.save(ann.state_dict(), filepath)

plt.plot(loss_list)
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.show()

plt.plot(avg_loss_list)
plt.show()
