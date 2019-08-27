numC1 = [-0.746];
denC1 = [537.2 1];
Gc1 = tf(numC1,denC1);
sys = ss(Gc1);
A = sys.A;
B = sys.B;
C = sys.C;
D = sys.D;