// src\pages\Cadastro\index.js

import FormularioCadastro from '../../components/FormularioCadastro'
import './styles.css'

function PaginaCadastro() {
    return (
        <div className='pagina-cadastro'>
            <h1>Cadastro de Usuário</h1>
            <FormularioCadastro/>
        </div>
    )
}

export default PaginaCadastro;
