clear all; clc; close all; clc;

%% Fun��o Transfer�ncia do Cooler
Ts = 25/1000;
numC = [-1.18];
denC = [270.9 1];

Gc = tf(numC,denC);

%% Fun��o Transfer�ncia da L�mpada

numL = [0.6783];
denL = [308.7 1];
Gl = tf(numL,denL);

%% Setando controlador para a L�mpada

CL = pidtune(6*Gl,'PID2')

%% Setando Controlador para o Cooler

CC = pidtune(6*Gc,'PID2',1.01)




