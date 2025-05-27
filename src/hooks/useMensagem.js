// src\hooks\useMensagem.js

import { useState, useCallback } from "react";

function useMensagem() {
    const [mensagem, setMensagem] = useState('');
    const [tipoMensagem, setTipoMensagem] = useState('');
    const [visivel, setVisivel] = useState(false);  // Agora é um booleano

    // Função para exibir a mensagem
    const exibirMensagem = useCallback((texto, tipo = 'sucesso') => {
        setMensagem(texto);
        setTipoMensagem(tipo);
        setVisivel(true);

        // Depois de 5 segundos, a mensagem desaparece
        setTimeout(() => setVisivel(false), 5000);
    }, []);

    // Função para fechar a mensagem manualmente
    const fecharMensagem = useCallback(() => {
        setVisivel(false);
    }, []);

    return { mensagem, tipoMensagem, visivel, exibirMensagem, fecharMensagem };
}

export default useMensagem;
