clear all; clc; close all; clc;

tic
[y cc cl ref] = comunicacao_step();
x = toc;


y = y(100:length(y));
cc = cc(100:length(cc));
cl = cl(100:length(cl));
ref = ref(100:length(ref));
t = 0:x/length(y):x;
t = t(1:(length(y)));



subplot(2,1,1);
plot(t,y,'k',t,ref,'--r');
% plot(t,y2,'k',t,y2,'b',t,ref2,'--r');
title('Temperatura em função da ação do cooler e da lâmpada');
ylabel('Temperatura em °C');
xlabel('Tempo (s)');
grid on;

subplot(2,1,2); 
plot(t,cc,'k',t,cl,'b');
title('Ação de controle');
ylabel('PWM (0-255)');
xlabel('Tempo (s)');
grid on;



