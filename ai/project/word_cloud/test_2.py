from sympy import plot_implicit, symbols, Eq

x, y = symbols('x y')
p2 = plot_implicit(Eq(x ** 3 - 3 * x ** 2 - 9 * x + 5, y), (x, -3, 5), (y, -30, 30))
