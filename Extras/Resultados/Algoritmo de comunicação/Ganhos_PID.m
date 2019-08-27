clear all; clc; close all; clc;

%% Função Transferência do Cooler
Ts = 25/1000;
numC = [-1.18];
denC = [270.9 1];

Gc = tf(numC,denC);

%% Função Transferência da Lâmpada

numL = [0.6783];
denL = [308.7 1];
Gl = tf(numL,denL);

%% Setando controlador para a Lâmpada

CL = pidtune(6*Gl,'PID2')

%% Setando Controlador para o Cooler

CC = pidtune(6*Gc,'PID2',1.01)




