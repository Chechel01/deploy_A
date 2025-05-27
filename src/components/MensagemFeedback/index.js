// src\components\MensagemFeedback\index.js

import './styles.css'

function MensagemFeedback({ mensagem, tipo, visivel, onclose }) {
    if (!visivel) {
        return null
    }

    return (
        <div 
            id='mensagem' 
            className={`mensagem ${tipo} visivel`}
            onClick={onclose}
        >
            <span className={`icone ${tipo}`}>&#x1F4AC;</span> {/* Ícone de conversa, você pode substituir pelo ícone adequado */}
            {mensagem}
        </div>
    )
}

export default MensagemFeedback
