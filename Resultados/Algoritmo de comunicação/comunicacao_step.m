function [y cc cl ref] = arduinoRead
N = 100000;

delete(instrfind('Port', 'COM4'));
s2 = serial('COM4', 'BaudRate', 9600);

fopen(s2);

for i = 1 : N
    
    sensor = fscanf(s2);
    if (i>1)
        sensor = strsplit(sensor)
        temperatura = sensor(1);
        controleC = sensor(2);
        controleL = sensor(3);
        referencia = sensor(4);
        y(i) = str2double(cell2mat(temperatura));
        cc(i) = str2double(cell2mat(controleC));
        cl(i) = str2double(cell2mat(controleL));
        ref(i) = str2double(cell2mat(referencia));
       
    end  
    
end

fclose(s2);

end

