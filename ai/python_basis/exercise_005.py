"""
将华氏温度转换为摄氏温度
F = 1.8C + 32

Author: 王威
"""
import math

f = float(input('请输入华氏温度: '))
c = (f - 32) / 1.8
print('%.1f华氏度 = %.1f摄氏度' % (f, c))

"""
输入圆的半径计算周长和面积

Author: 王威
"""

radius = float(input('请输入圆的半径：'))
perimeter = 2 * math.pi * radius
print('周长: %.2f' % perimeter)
area = math.pi * radius ** 2
print('面积: %.2f' % area)

"""
输入年份 如果是闰年输出True 否则输出False

Author: 王威
"""
year = int(input('请输入年份: '))
is_leap = (year % 4 == 0 and year % 100 != 0 or year % 400 == 0)
print(is_leap)
