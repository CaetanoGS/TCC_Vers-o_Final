% delete(instrfind('Port', 'COM4'));
% s = serial('COM4', 'BaudRate', 9600);
% fclose(s);
% fopen(s);
% voltage = fscanf(s)
% fprintf(s, 'a');
% fclose(s);

s = tf('s');

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

%Matriz de Transformaci?n(Tc)
    %Matriz de Controlabilidad
        Mco=[B A*B]
        Mcc=[Bc Ac*Bc];
        Tc=Mco*Mcc^-1
    
%Matriz de Transformaci?n(To)
    %Matriz de Observabilidad
        Mo=[C;C*A]
        Moc=[Co;Co*Ao];
        To=Mo^-1*Moc
      
%Forma Can?nica Controlable
        Azc=Tc^-1*A*Tc
        Bzc=Tc^-1*Bc
        Czc=     C*Tc
%Forma Can?nica Observable
        Azo=To^-1*A*To
        Bzo=To^-1*B
        Czo=     C*To
%Calulo de Estados y Salidas
xo=[1;0];I=[1 0;0 1];U=1;
syms s t T
disp('Matriz Transicion de Estado')
eat1=ilaplace([s*I-A]^-1)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
eat2=eat1*B*U
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
   xt2=int([exp(-t+T) - exp(-2*t+2*T); 2*exp(-2*t+2*T) - exp(-t+T)],T,0,t);
   xt2=expand(xt2)
   disp('Salida')
   xt=simplify(eat1*xo+xt2)
   
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%t=linspace(0,10,150);
[y,t,x]=initial(s1,xo);
%initial(s1,xo)
x1=x(:,1);
x2=x(:,2);
subplot(2,2,1),plot(x1,x2),subplot(2,2,2),plot(t,x1,t,x2);
    