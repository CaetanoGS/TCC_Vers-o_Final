clc; clear all; close all; clc;

%% Funções transferência do cooler

numC = [6*-1.18];
denC = [270.9 1];

Ts = 25/1000;
Gc = tf(numC,denC,Ts);


%% Funções Transferência da Lâmpada

numL = [6*0.6783];
denL = [308.7 1];


Gl = tf(numL,denL,Ts);


%% Matriz de Função Transferência

% sys = ss(Gc);
sys = ss(Gl);
A = sys.a;
B = sys.b;
C = sys.c;
D = sys.d;


% Especificacao
acel = 1;
Pd = eig(A)*acel;

%% Analise do Sistema
Co = rank(ctrb(A,B));
%% Precompensador
K = place(A,B,Pd);
N = -1./(C*inv(A-B*K)*B);

%% Integrador 
na = size(A);
nb = size(B);
nc = size(C);
Aa = [A zeros(na(1),nc(1));-C zeros(nc(1))];
Ba = [B; zeros(nc(1),nb(2))];
%analise do sistema
Co = rank(ctrb(Aa,Ba));
%especificacao
% Pdi = [Pd; -0.05722;];
Pdi = [(-0.0360 + 0.0225i) (-0.0360 - 0.0225i)];
KK = place(Aa,Ba,Pdi);
Kx = KK(:,1:na(1))
Ki = KK(:,na(1)+1:end)

