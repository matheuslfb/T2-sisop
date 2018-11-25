# Sistemas Operacionais – Trabalho Gerência de Memória - Prof. Avelino Zorzo



Partições variáveis
Implementar um programa que controla a alocação de memória utilizando "gerência de memória por partições variáveis".
1. O gerente deve receber um bloco que representa a memória disponível do endereço mi até o endereço mf. 
2. A partir deste bloco, o gerente recebe solicitações de alocação de memória e solicitações de liberação de memória.
3. A solicitação de memória deve retornar um identificador para a área de memória que foi alocada, enquanto o comando de liberação de memória envia o identificador recebido durante a alocação. 
4. O programa deve informar o momento que houver fragmentação externa no sistema. Neste momento deve mostrar como a memória está organizada, ou seja, quais os blocos ocupados e quais os blocos livres.
5. O programa deve receber as solicitações e liberações via o arquivo de entrada de dados
6. Não é preciso controlar tempo, as alocações e liberações são realizadas na ordem que chegarem e puderem ser atendidas. Se uma alocação não puder ser atendida, deve ser verificado se ela pode ser atendida no momento que uma liberação acontecer.
7. Deve haver alguma forma de acompanhar (visualizar) o que está acontecendo no programa a cada solicitação ou liberação.
Exemplo:
```
1     // modo fixo - para o modo aleatório este valor será 2 
100   // mi
1250  // mf
S 250 // bloco 1
S 100 // bloco 2
S 200 // bloco 3
L 2   // libera bloco 2
S 150 // bloco 4
S 150 // bloco 5
S 150 // bloco 6
L 5 // libera bloco 5
S 200 // neste momento existe fragmentação
Exemplo de saída quando acontecer fragmentação.
100-350   bloco 1 (tamanho 250)
350-450   livre (tamanho 100)
450-650   bloco 3 (tamanho 200)
650-800   bloco 4 (tamanho 150)
800-950   livre (tamanho 150)
950-1100  bloco 6 (tamanho 150)
1100-1250 livre (tamanho 150)
400 livres, 200 solicitados - fragmentação externa.
```
**Data da entrega: 22.11.2018
Entregar código e documentação, conforme formato fornecido. Trabalho individual.**
