import tensorflow as tf

# 定义常量操作
hello = tf.constant("Hello,TensorFlow")

# 创建一个会话
sess = tf.Session()

# 执行常量操作hello并打印到标准输出
print(sess.run(hello))
