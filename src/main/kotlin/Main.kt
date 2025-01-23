package org.example

class Usuario(val id: Long, var nome: String)

fun imprimeNomeUsuario(usuario: Usuario) {
    println(usuario.nome)
    usuario.nome = "teste"
}

class ListaUsuarioIdempotente() {
    val usuarios = mutableListOf<Usuario>()

    fun inserir(id: Long, nome: String) {
        // tratamento para ser idempotente
        if (usuarios.find {
                it.id == id && it.nome == nome
            } == null ) {
            usuarios.add(Usuario(id, nome))
        }
    }

    fun listar() {
        usuarios.forEach { println(it.nome) }
    }
}

fun main() {
    //efeito colateral e nao idempotencia
    val usuario = Usuario(1, "Gustavo")
    imprimeNomeUsuario(usuario)
    imprimeNomeUsuario(usuario)

    // idempotencia
    val listaUsuarioIdempotente = ListaUsuarioIdempotente()
    listaUsuarioIdempotente.inserir(1, "Gustavo")
    listaUsuarioIdempotente.inserir(1, "Gustavo")
    listaUsuarioIdempotente.inserir(1, "Gustavo")
    listaUsuarioIdempotente.inserir(1, "Gustavo")

    listaUsuarioIdempotente.listar()

}
