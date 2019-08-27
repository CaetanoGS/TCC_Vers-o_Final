clear all; clc; close all; clc;

tic
[y cc cl ref] = comunicacao_step();
% [y cc cl] = comunicacao_step();
x = toc;

N = 100;

y = y(N:length(y));
cc = cc(N:length(cc));
cl = cl(N:length(cl));
ref = ref(N:length(ref));
t = 0:x/length(y):x;
t = t(1:(length(y)));


%% Configuração que funciona

limitsT = [27.5 34.5];   % limites do eixo Y para os sinais de saida e referencia
limitsU = [0 300];
limitsX = [0 x];

% y_1 = y(5000:(length(y) - 1000)); plot(y_1);

subplot(2,1,1);
xlim = (limitsX);
plot(t,y,'k',t,ref,'--r');
% plot(t,y,'k');
% plot(t,y,'k',t,y_PID,'b',t,ref,'--r');
ylim(limitsT);
title('Controle de Temperatura');
ylabel('Temperatura em °C');
xlabel('Tempo (s)');
legend('y(t)','Ref')
grid on;

subplot(2,1,2);
xlim = (limitsX);
ylim(limitsU);
plot(t,cc,'k',t,cl,'b');
title('Ação de Controle');
ylabel('PWM (0-255)');
xlabel('Tempo (s)');
legend('Uc(t)', 'Ul(t)')
grid on;


