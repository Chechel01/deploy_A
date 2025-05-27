// src/components/FormularioCadastro/index.js

import { useState } from "react";
import './styles.css'
import { useNavigate } from "react-router-dom";
import useMensagem from '../../hooks/useMensagem'
import MensagemFeedback from '../MensagemFeedback'
import logo from '../../assets/images/logoBahia.jpg'
import axios from 'axios'

function FormularioCadastro() {
    const [id, setId] = useState('')
    const [nome, setNome] = useState('')
    const [sexo, setSexo] = useState('')
    const [idade, setIdade] = useState('')
    const [altura, setAltura] = useState('')
    const [peso, setPeso] = useState('')
    const [posicao, setPosicao] = useState('')
    const [numCamisa, setNumCamisa] = useState('')
    const [loading, setLoading] = useState(false) // Estado para controlar o carregamento
    const navigate = useNavigate()
    const { exibirMensagem , mensagem, tipoMensagem, visivel, fecharMensagem } = useMensagem()

    const cadastrarUsuario = async () => {
        // Validação simples no front-end
        if (!nome || !sexo || !idade || !altura || !peso || !posicao || !numCamisa) {
            exibirMensagem('Todos os campos são obrigatórios.', 'erro');
            return;
        }

        setLoading(true);  // Inicia o carregamento

        try {
            const response = await axios.post('https://tcc-backend-senai-8e1w.onrender.com/usuarios', {
                nome,
                sexo,
                idade,
                altura,
                peso,
                posicao,
                numCamisa
            })
            exibirMensagem(response.data.mensagem || 'Usuário cadastrado com sucesso!', 'sucesso')
            
            // Limpar os campos após o cadastro
            setId('')
            setNome('')
            setSexo('')
            setIdade('')
            setAltura('')
            setPeso('')
            setPosicao('')
            setNumCamisa('')
        } catch (error) {
            let erroMsg = 'Erro ao conectar ao servidor.'
            if (error.response && error.response.data) {
                erroMsg = error.response.data.mensagem
                if (error.response.data.erros) {
                    erroMsg += ' ' + Object.values(error.response.data.erros).join(', ')
                }
            }
            exibirMensagem(erroMsg, 'erro')
        } finally {
            setLoading(false);  // Finaliza o carregamento
        }
    }

    return (
        <div className="container">
            <img src={logo} alt="Logo da empresa" />
            <h2>Cadastro de usuários</h2>
            <form onSubmit={(e) => {e.preventDefault(); cadastrarUsuario()}}>
                <input 
                    type="text"
                    id="id"
                    placeholder="ID"
                    value={id}
                    onChange={(e) => setId(e.target.value)}
                    required
                />
                <input 
                    type="text"
                    id="nome"
                    placeholder="Nome"
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                    required
                />
                <input 
                    type="text"
                    id="sexo"
                    placeholder="Sexo"
                    value={sexo}
                    onChange={(e) => setSexo(e.target.value)}
                    required
                />
                <input 
                    type="number"
                    id="idade"
                    placeholder="Idade"
                    value={idade}
                    onChange={(e) => setIdade(e.target.value)}
                    required
                />
                <input 
                    type="number"
                    id="altura"
                    placeholder="Altura (em metros)"
                    value={altura}
                    onChange={(e) => setAltura(e.target.value)}
                    required
                />
                <input 
                    type="number"
                    id="peso"
                    placeholder="Peso (em kg)"
                    value={peso}
                    onChange={(e) => setPeso(e.target.value)}
                    required
                />
                <input 
                    type="text"
                    id="posicao"
                    placeholder="Posição"
                    value={posicao}
                    onChange={(e) => setPosicao(e.target.value)}
                    required
                />
                <input 
                    type="number"
                    id="numCamisa"
                    placeholder="Número da camisa"
                    value={numCamisa}
                    onChange={(e) => setNumCamisa(e.target.value)}
                    required
                />
                <button type="submit" disabled={loading}>
                    {loading ? 'Cadastrando...' : 'Cadastrar'}
                </button>
            </form>

            <button onClick={() => navigate('/usuarios')} className="link-usuarios">
                Ver usuários cadastrados
            </button>

            <MensagemFeedback
                mensagem={mensagem}
                tipo={tipoMensagem}
                visivel={visivel}
                onclose={fecharMensagem}
            />
        </div>
    )
}

export default FormularioCadastro
