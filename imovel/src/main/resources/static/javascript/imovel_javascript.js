var swiper = new Swiper(".mySwiper", {
    slidesPerView: 'auto',
    pagination: {
        el: ".swiper-pagination",
        dynamicBullets: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",  
    },
    
    breakpoints: {
      640: {
        slidesPerView: 2,
        // spaceBetween: 20,
      },
      854: {
        slidesPerView: 3,
        // spaceBetween: 40,
      },
      1280: {
        slidesPerView: 4,
        // spaceBetween: 60,
      },
      1920: {
        slidesPerView: 5,
        // spaceBetween: 50,
      },
      2560: {
        slidesPerView: 7,
        // spaceBetween: 50,
      },
    },
  });

//   function buscaCep(){
//     const cep = document.getElementById('cep').ariaValueMax;
//     if (cep.length ===8){
//         fetch(`https://viacep.com.br/ws/${cep}/json/`).then(response => response.json()).then(data => {if (!data.erro){
//             document.getElementById('logradouro').value = data.logradouro;
//             // document.getElementById('inputCity').value = data.bairro;
//             document.getElementById('cidade').value = data.localidade;
//             document.getElementById('estado').value = data.uf;
//     }else{
//         alert('CEP não encontrado.');
//     }
//         }).catch(error => {console.error('Erro na busca do CEP: ',error);})
//     }
//   }


function limpa_formulário_cep() {
    //Limpa valores do formulário de cep.
    document.getElementById('rua').value=("");
    // document.getElementById('bairro').value=("");
    document.getElementById('cidade').value=("");
    document.getElementById('uf').value=("");
    // document.getElementById('ibge').value=("");
}

function meu_callback(conteudo) {
if (!("erro" in conteudo)) {
    //Atualiza os campos com os valores.
    document.getElementById('rua').value=(conteudo.logradouro);
    // document.getElementById('bairro').value=(conteudo.bairro);
    document.getElementById('cidade').value=(conteudo.localidade);
    document.getElementById('uf').value=(conteudo.uf);
    // document.getElementById('ibge').value=(conteudo.ibge);
} //end if.
else {
    //CEP não Encontrado.
    limpa_formulário_cep();
    alert("CEP não encontrado.");
}
}

function pesquisacep(valor) {

//Nova variável "cep" somente com dígitos.
var cep = valor.replace(/\D/g, '');

//Verifica se campo cep possui valor informado.
if (cep != "") {

    //Expressão regular para validar o CEP.
    var validacep = /^[0-9]{8}$/;

    //Valida o formato do CEP.
    if(validacep.test(cep)) {

        //Preenche os campos com "..." enquanto consulta webservice.
        document.getElementById('rua').value="...";
        // document.getElementById('bairro').value="...";
        document.getElementById('cidade').value="...";
        document.getElementById('uf').value="...";
        // document.getElementById('ibge').value="...";

        //Cria um elemento javascript.
        var script = document.createElement('script');

        //Sincroniza com o callback.
        script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

        //Insere script no documento e carrega o conteúdo.
        document.body.appendChild(script);

    } //end if.
    else {
        //cep é inválido.
        limpa_formulário_cep();
        alert("Formato de CEP inválido.");
    }
} //end if.
else {
    //cep sem valor, limpa formulário.
    limpa_formulário_cep();
}
};

//PÁGINA DO PERFIL

function abc(){
  document.getElementById("getdesc1").innerHTML=localStorage.guardarNome;
}

function descricao(){ //funçao para adicionar descrição
  var descricao = prompt('Digite a descrição do seu perfil')
  var msg = document.getElementById("getdesc1");

  msg = descricao;
  document.getElementById("getdesc1").innerHTML = msg;
  // alert(descricao);
  localStorage.guardarNome = descricao;
}