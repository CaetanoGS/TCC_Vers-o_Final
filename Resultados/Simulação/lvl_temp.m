clc; clear all; close all; clc;

lvl = load('lvl.txt');
temp = load('temp.txt');
t = 0.1:(80/180):80;
t = t';
janela = 5;
windowSize = janela; 
b = (1/windowSize)*ones(1,windowSize);
a = 1;
temp_filter = filter(b,a,temp);
temp_filter = temp_filter(janela:180);
lvl_filter = filter(b,a,lvl);
lvl_filter = lvl_filter(janela:180);
Ub = 128*ones(1,30);
Ub = [Ub 200*ones(1,(180-30))];
Ur = 70*ones(1,180);
% figure;
% subplot(2,1,1)
% plot(t(janela:180),lvl_filter);
% xlabel('Tempo (s)');
% ylabel('Altura (cm)');
% title('Teste em Malha aberta');
% legend('Nível')
% subplot(2,1,2)
% plot(t,Ub, 'r')
% xlabel('Tempo (s)');
% ylabel('PWM (0 - 255)');
% title('Ação de controle');
% legend('Ub')

% figure;
% subplot(2,1,1)
% plot(t(janela:180),temp_filter);
% xlabel('Tempo (s)');
% ylabel('Temperatura (°C)');
% title('Teste em Malha aberta');
% legend('Temperatura')
% subplot(2,1,2)
% plot(t,Ur, 'r')
% xlabel('Tempo (s)');
% ylabel('Tensão na resistência (Vac)');
% title('Ação de controle');
% legend('Ur')

%% ---------------- Definindo a FT ----------------------

s = tf('s');
TF_L_T = minreal((0.63/15)/((s+0.066)*(s+1))); %Função transferência que relaciona nível e temperatura

% figure;
% subplot(2,1,1)
% plot(t(janela:180),temp_filter);
% xlabel('Tempo (s)');
% ylabel('Temperatura (°C)');
% title('Teste em Malha aberta');
% legend('Temperatura')
% subplot(2,1,2)
% stepplot(29.06+TF_L_T,'r')
% xlabel('Tempo');
% ylabel('Temperatura (°C)');
% title('Estimativa de FT');
% legend('Temperatura')

%% Discretização 

num = 0.042;
den = [1 1.066 0.066];

[numGz, denGz] = c2dm(num,den,0.1);
Gz = tf(numGz, denGz,0.1);


% figure;
% 
% subplot(2,1,1)
% stepplot(TF_L_T);
% xlabel('Tempo');
% ylabel('Temperatura (°C)');
% title('Reposta ao Degrau em tempo contínuo');
% legend('G(s)')
% subplot(2,1,2)
% stepplot(Gz, 'r')
% xlabel('Tempo');
% ylabel('Temperatura (°C)');
% title('Resposta ao degrau em tempo discreto');
% legend('G(z)')




TF_L_T = minreal((0.63/15)/((s+0.066)*(s+1))); %Função transferência que relaciona nível e temperatura
TF_T = minreal((4.19*0.001708)/((s+0.01708)*(s+0.1))); % FT da temperatura ao degrau
TF_L = minreal(0.66/((s+1)*(s+0.089))); %FT do nível ao degrau

M_FT = [TF_L 0;
        TF_T TF_L_T]
        
sys = ss(M_FT);

A = sys.a;
B = sys.b;
C = sys.c;
D = sys.d;

% step(M_FT);
% hold;
% step(sys)

% Especificacao
acel = 3;
% Pd = eig(A)*acel;
Pd = [-2 -0.178 -1.0000 -0.132 -0.2000 -0.0342]

%% Analise do Sistema
Co = rank(ctrb(A,B))
%% Precompensador
K = place(A,B,Pd)
N = -1./(C*inv(A-B*K)*B)

%% Integrador 
na = size(A);
nb = size(B);
nc = size(C);
Aa = [A zeros(na(1),nc(1));-C zeros(nc(1))]
Ba = [B; zeros(nc(1),nb(2))];
%analise do sistema
Co = rank(ctrb(Aa,Ba))
%especificacao
Pdi = [Pd -0.1 -0.2];
KK = place(Aa,Ba,Pdi)
Kx = KK(:,1:na(1));
Ki = KK(:,na(1)+1:end);

%% Observador

Pdo = 1*Pd;
Ko = place(A',C',Pdo);
L = Ko';

Co = obsv(A,C);
[n n] = size(A);

if rank(Co) == n
    'Sistema Observavel'
end