import tensorflow as tf

# 主要掌握张量的概念
'''
一、在数学中，张量是一种几何实体，广义上表示任意形式的"数据"。
二、张量可以理解为0阶标量、1阶向量、2阶矩阵在高维空间上的推广，张量的阶描述它表示数据的最大维度。
三、在TensorFlow中，张量（Tensor）表示某种相同数据类型的多维数据。
四、张量的两个重要属性：（1）数据类型，例如浮点型、整型、字符串； （2）数组形状，各个维度的大小
五、TensorFlow张量是什么？
    1、张量是用来表示多维数据的
    2、张量是执行操作时的输入或者输出数据
    3、用户通过执行操作来创建或者计算张量
    4、张量的形状不一定在编译时确定，可以在运行时通过形状推断计算得出
六、TensorFlow中，有几类比较特别的张量需要通过以下操作产生：
    1、tf.constant 常量
    2、tf.placeholder 占位符
    3、tf.Variable 变量
'''
# 0阶张量（也称为标量）
animal = tf.Variable("Elephant", tf.string)
integer = tf.Variable(451, tf.int16)
floating = tf.Variable(3.14159265359, tf.float64)
its_complicated = tf.Variable(12.3 - 4.852j, tf.complex64)

# 1阶张量(也称为向量)
my_str = tf.Variable(["Elephant", "Hello"], tf.string)
first_primes = tf.Variable([451, 123], tf.int16)
cool_numbers = tf.Variable([3.14159265359, 2.1828], tf.float64)
its_very_complicated = tf.Variable([12.3 - 4.852j, 7.5 - 6.23j], tf.complex64)

# 2阶张量（也称为矩阵）
my_mat_str = tf.Variable([["Elephant"], ["Hello"]], tf.string)
first_mat_primes = tf.Variable([[451], [123], [123], [123]], tf.int16)
cool_mat_numbers = tf.Variable([[3.14159265359], [2.1828]], tf.float64)
its_very_mat_complicated = tf.Variable([[12.3 - 4.852j], [7.5 - 6.23j]], tf.complex64)

# 3阶张量
my_num = tf.zeros([10, 299, 299])
