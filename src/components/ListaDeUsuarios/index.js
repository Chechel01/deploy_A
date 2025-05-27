import { useState, useEffect } from "react";
import axios from "axios";
import './styles.css'

function ListaDeUsuarios() {
    const [usuarios, setUsuarios] = useState([]);
    const [erro, setErro] = useState(''); // Adicionando estado para erro

    useEffect(() => {
        const carregarUsuarios = async () => {
            try {
                const response = await axios.get('https://tcc-backend-senai-8e1w.onrender.com/usuarios');
                setUsuarios(response.data);
            } catch (error) {
                setErro('Erro ao buscar usuários. Tente novamente mais tarde.');
                setUsuarios([]);
            }
        };
        carregarUsuarios();
    }, []);

    return (
        <div className="container-usuarios">
            {/*<h2>Lista de Usuários</h2>*/}

            {/* Exibição de erro, caso haja */}
            {erro && <div className="erro">{erro}</div>}

            <ul id="listaUsuarios" className="lista-usuarios">
                {usuarios.length === 0 ? (
                    <li className="sem-usuarios">Nenhum usuário encontrado.</li>
                ) : (
                    usuarios.map(usuario => (
                        <li key={usuario.id} className="usuario-item">
                            <strong>ID: </strong> {usuario.id}<br />
                            <strong>Nome: </strong> {usuario.nome}<br />
                            <strong>Sexo: </strong> {usuario.sexo}<br />
                            <strong>Idade: </strong> {usuario.idade}<br />
                            <strong>Altura: </strong> {usuario.altura}<br />
                            <strong>Peso: </strong> {usuario.peso}<br />
                            <strong>Posição: </strong> {usuario.posicao}<br />
                            <strong>Número da camisa: </strong> {usuario.numCamisa}           
                        </li>
                    ))
                )}
            </ul>
        </div>
    );
}

export default ListaDeUsuarios;
