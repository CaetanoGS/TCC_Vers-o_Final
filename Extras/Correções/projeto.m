clear all; close all; clc;


Ts = 25/1000;


%% Funções Transferência da Lâmpada
s = tf('s');

numL = [6*0.6783];
denL = [308.7 1];

Gl = tf(numL,denL);

Kp = 38;
Ki = 20;
Kd = 0.1;

Cs = zpk((Kd*s^2 + Kp*s + Ki)/s);
pid(Cs);

Gl_d = zpk(c2d(Gl,Ts,'tustin'));
Cs_d = zpk(c2d(Cs,Ts,'tustin'));






%% Definição dos pólos em malha fechada
Hr = zpk(((Gl*Cs)/(1+(Gl*Cs))));


%% EE

sys = ss(Gl);

A = sys.A;
B = sys.B;
C = sys.C;
D = sys.D;

%% Especificacao

acel = 1;
Pd = eig(A)*acel;

%% Analise do Sistema

Co = rank(ctrb(A,B));

%% Precompensador

K = place(A,B,Pd);
N = -1/(C*inv(A-B*K)*B);

%% Integrador 

na = size(A);
nb = size(B);
nc = size(C);
Aa = [A zeros(na(1),nc(1));-C zeros(nc(1))];
Ba = [B; zeros(nc(1),nb(2))];

%% Analise do sistema

Co = rank(ctrb(Aa,Ba));

%% Especificacao

Pdi = [(-0.0360 + 0.0225i) (-0.0360 - 0.0225i)];
KK = place(Aa,Ba,Pdi);
Kx = KK(1:na(1));
Ki = KK(na(1)+1:end);

% %% Projeto para o cooler
% 
% numC = [-7.08];
% denC = [270.9 1];
% Gc = tf(numC,denC);
% 
% s = tf('s');
% 
% Kp = -38;
% Ki = -3;
% Kd = -0.1;
% 
% Cs = (Kd*s^2 + Kp*s + Ki)/s;
% % Cs = (-45.962*(s+66.06)*(s+0.07904))/(s*(s+80));
% 
% pid(Cs);
% zpk(Cs);
% 
% Hr = ((Gc*Cs)/(1+(Gc*Cs)));
% Hr = zpk(Hr);
% 
% % 
% %   0.0026067 (s+379.9) (s+0.07896)
% %   -------------------------------
% %      (s+0.9081) (s+0.08611) 
% % 
% 
% %% Discretizando o controlador e processo com método bilinear
% 
% Ts = 25/1000;
% 
% z = tf('z',Ts);
% 
% Cs_d = zpk(c2d(Cs,Ts,'tustin'));
% Gc_d = zpk(c2d(Gc,Ts,'tustin'));
% Hr_d = zpk(c2d(Hr,Ts,'tustin'));
% 
% %   0.014816  (z-0.998) (z+0.6521)
% %   ------------------------------
% %       (z-0.9978) (z-0.9776)
% 
% 
% %% EE
% 
% sys = ss(Gc);
% 
% A = sys.A;
% B = sys.B;
% C = sys.C;
% D = sys.D;
% 
% %% Especificacao
% 
% acel = 1;
% Pd = eig(A)*acel;
% 
% %% Analise do Sistema
% 
% Co = rank(ctrb(A,B));
% 
% %% Precompensador
% 
% K = place(A,B,Pd);
% N = -1/(C*inv(A-B*K)*B);
% 
% %% Integrador
% 
% na = size(A);
% nb = size(B);
% nc = size(C);
% Aa = [A zeros(na(1),nc(1));-C zeros(nc(1))];
% Ba = [B; zeros(nc(1),nb(2))];
% 
% %% Analise do sistema
% 
% Co = rank(ctrb(Aa,Ba));
% 
% %% Especificacao
% 
% Pdi = [-0.9081 -0.08611];
% KK = place(Aa,Ba,Pdi);
% Kx = KK(1:na(1))
% Ki = KK(na(1)+1:end)
% 
% %% EE
% 
% sys_d = ss(Gc_d);
% 
% A_d = sys_d.A;
% B_d = sys_d.B;
% C_d = sys_d.C;
% D_d = sys_d.D;
% 
% %% Especificacao
% 
% acel_d = 100;
% Pd_d = eig(A_d)*acel_d;
% 
% %% Analise do Sistema
% 
% Co_d = rank(ctrb(A_d,B_d));
% 
% %% Precompensador
% 
% K_d = place(A_d,B_d,Pd_d);
% N_d = -1/(C_d*inv(A_d-B_d*K_d)*B_d);
% 
% %% Integrador
% 
% na_d = size(A_d);
% nb_d = size(B_d);
% nc_d = size(C_d);
% Aa_d = [A_d zeros(na_d(1),nc_d(1));-C_d zeros(nc_d(1))];
% Ba_d = [B_d; zeros(nc_d(1),nb_d(2))];
% 
% %% Analise do sistema
% 
% Co_d = rank(ctrb(Aa_d,Ba_d));
% 
% %% Especificacao
% 
% Pdi_d = [-0.053 -0.05];
% KK_d = place(Aa_d,Ba_d,Pdi_d);
% Kx_d = KK_d(1:na_d(1))
% Ki_d = KK_d(na_d(1)+1:end)