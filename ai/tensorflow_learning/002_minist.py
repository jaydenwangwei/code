import tensorflow as tf

# 导入MNIST数据集
from tensorflow.examples.tutorials.mnist import input_data

mnist = input_data.read_data_sets("MNIST_data/", one_hot=True)

# 定义超参数
learning_rate = 0.1
num_steps = 500
batch_size = 128
display_step = 100

# 定义神经网络参数
# 第一层神经元个数
n_hidden_1 = 256
# 第一层神经元个数
n_hidden_2 = 256
# MNIST输入数据（图像大小，28*28）
num_input = 784
# MNIST手写体数字类别（0-9）
num_classes = 10

# 输入到数据流中的训练数据
X = tf.placeholder("float", [None, num_input])
Y = tf.placeholder("float", [None, num_classes])

# 权重
weights_1 = tf.Variable(tf.random_normal([num_input, n_hidden_1]))
weights_2 = tf.Variable(tf.random_normal([n_hidden_1, n_hidden_2]))
weights_out = tf.Variable(tf.random_normal([n_hidden_2, num_classes]))

# 偏置
biases_1 = tf.Variable(tf.random_normal([n_hidden_1]))
biases_2 = tf.Variable(tf.random_normal([n_hidden_2]))
biases_out = tf.Variable(tf.random_normal([num_classes]))


# 定义神经网络
def neural_net(x):
    # 第一层隐藏层（256个神经元）
    layer_1 = tf.add(tf.matmul(x, weights_1), biases_1)
    # 第二层隐藏层（256个神经元）
    layer_2 = tf.add(tf.matmul(layer_1, weights_2), biases_2)
    # 输出层
    layer_out = tf.add(tf.matmul(layer_2, weights_out), biases_out)
    return layer_out


# 构建模型
logits = neural_net(X)

# 定义损失函数
loss_op = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=logits, labels=Y))

# 定义优化器
optimizer = tf.train.AdadeltaOptimizer(learning_rate=learning_rate)
train_op = optimizer.minimize(loss_op)

# 定义预测准确率
correct_pred = tf.equal(tf.argmax(logits, 1), tf.argmax(Y, 1))
accuracy = tf.reduce_mean(tf.cast(correct_pred, tf.float32))

# 初始化所有变量
init = tf.global_variables_initializer()

# 开始训练
with tf.Session() as sess:
    # 执行初始化操作
    sess.run(init)
    for step in range(1, num_steps + 1):
        batch_x, batch_y = mnist.train.next_batch(batch_size)
        # 执行训练操作，包括向前向后传播
        if step % display_step == 0 or step == 1:
            loss, acc = sess.run([loss_op, accuracy], feed_dict={X: batch_x, Y: batch_y})
            print("step" + str(step) + ",MiniBatch Loss=" + "{:.4f}".format(
                loss) + ",Training Accuracy = " + "{:.3f}".format(acc))

    print("优化完成！")

    # 计算测试数据的准确率
    print("Testing Accuracy:", sess.run(accuracy, feed_dict={X: mnist.test.images, Y: mnist.test.labels}))
