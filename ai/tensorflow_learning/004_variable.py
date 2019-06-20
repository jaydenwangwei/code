import tensorflow as tf

# 主要掌握变量的概念
'''
一、在TensorFlow中，Variable（变量）的主要作用是维护特定节点的状态，如深度学习或者机器学习的模型参数。
二、tf.Variable是操作，返回是变量（特殊张量）
三、通过tf.Variable创建的变量，与张量一样，可以作为操作的输入和输出，不同之处在于：
    1、张量的生命周期通常随依赖的计算完成而结束，内存也随即释放。
    2、变量则常驻内存，在每一步训练时不断更新其值，以实现模型参数的更新。
四、步骤
    1、创建变量
        w = tf.Variable( < initial - value >, name = < optinal - name >)
    2、将变量作为操作的输入
        y = tf.matmul(w, ...another variable or tensor)
        z = tf.sigmoid(w + y)
    3、使用 assign 或者 assign_xxx 方法重新给变量赋值
        w.assign(w + 1.0)
        w.assign_add(1.0)
    4、保存变量
        saver = tf.train.Saver(..., name='v1')   
        saver.save(sess, 'test-model', global_step=0)
    5、恢复变量
        saver.restore(sess, '/Users/jaydenwangwei/Desktop/model/test.ckpt-0')
    6、恢复数据流图结构
        tf.train.import_meta_graph()
        
'''
# 1、创建变量。tf.random_normal方法返回（1，4）的张量。它的四个元素符合均值为100、标准差为0.35的正态分布
w = tf.Variable(initial_value=tf.random_normal(shape=(1, 4), mean=100, stddev=0.35), name='w')
b = tf.Variable(tf.zeros([4], name='b'))

# 2、初始化变量
sess = tf.Session()
sess.run(tf.global_variables_initializer())
print(sess.run([w, b]))

# 3、执行更新变量b的操作
sess.run(tf.assign_add(b, [1, 1, 1, 1]))

# 4、查看变量b是否更新成功
print(sess.run(b))

saver = tf.train.Saver({'w': w, 'b': b})

# 5、保存变量
saver.save(sess, '/Users/jaydenwangwei/Desktop/model/test.ckpt', global_step=0)

# 6、恢复变量
saver.restore(sess, '/Users/jaydenwangwei/Desktop/model/test.ckpt-0')
print(sess.run(b))

# 7、恢复数据流结构
# tf.train.import_meta_graph()
