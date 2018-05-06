# Carteira Virtual


## Apresentação
Aplicativo voltado para o gerenciamento de uma carteira virtual, a qual gerencia 3 contas, sendo duas de criptomoedas e uma outra que especifica o saldo em dinheiro (BRL) que o usuário contém de modo que transações de compra, venda e troca são realizadas.


## Dependências 
O aplicativo usa o Retrofit para gerenciar requisições para a API do [Banco Central](https://dadosabertos.bcb.gov.br/dataset/taxas-de-cambio-todos-os-boletins-diarios) e do [Mercado Bitcoin](https://www.mercadobitcoin.net/api-doc/). Esta dependência já está adicionada no arquivo gradle do aplicativo. Mais informações sobre como usar o retrofit acesse [Retrofit](http://square.github.io/retrofit/).

## Como usar

1. [Clone](https://git-scm.com/book/pt-br/v1/Git-Essencial-Obtendo-um-Reposit%C3%B3rio-Git) este repositório;
2. Abra o projeto no [Android Studio](https://developer.android.com/studio/index.html?hl=pt-br);
3. Execute-o no seu smartphone ou em algum Emulador;
4. Faça o registro de um usuário informando o seu número de telefone. Um token será enviado via SMS para o seu celular;
5. Digite o Token no campo indicado e confirme;
6. Se o token for válido você será redirecionado para uma tela de cadastro;
7. Cadastre-se e use as informações cadastradas para fazer o login;
8. Digite o número do telefone e a senha cadastrados e clique em Login;
9. Se as informações fornecidas forem as mesmas cadastradas você será redirecionado para a Tela principal a qual contém as informações de saldo de todas as contas do usuário, além de apresentar a cotação de compra e venda de cada criptomoeda;
10. Ao arrastar a tela para o lado ou clicar em algum dos ícones na barra superior da tela, você será redirecionado para o extrato das transações ou para as informações do perfil do usuário de acordo com a opção escolhida;
11. No extrato é possível visualizar a data e as transações realizadas, podendo ser venda ou compra de alguma das criptomoedas ou troca entre elas;
12. No perfil do usuário, é possível visualizar e editar as informações do usuário;

## Status do Projeto 

O projeto está em fase de desenvolvimento.

### Features implementadas
1. Registro de novo usuário com validação via geração de token; 
2. Login do usuário com número de telefone e senha;
3. Gerenciamento de transações;
4. Tela com o saldo das contas do usuário;
5. Visualização e edição do perfil do usuário;
6. Extrato das transações
6. Recuperação de senha;


## Licença

```MIT License

Copyright (c) 2018 Samila Ruane

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE
```



