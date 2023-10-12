package br.com.mvteus.todolist.filter;

/*import jakarta.servlet.*;
import org.springframework.stereotype.Component;
import java.io.IOException;*/

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

public class FilterTaskAuth extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*
         *
         * Toda requisição, antes de passar pela rota, será filtrado
         * e executará alguma ação se tiver permissão
         * após autenticação através do Basic Auth
         * que seria validar usuário e senha
         * */

        var authorization = request.getHeader("Authorization");

        /*
        * Retorna a senha codificada em Base64
        * */

        var authEncoded = authorization.substring("Basic".length()).trim();

        /*
        * É retornado a autenticação codificada como 'Basic Base64passwordencoded'
        * realizo o substring para remover o 'Basic' e
        * trim para remover o espaço em branco
        * */

        byte[] authDecode = Base64.getDecoder().decode(authEncoded);
        var authString = new String(authDecode);

        /*
        * Realizamos a decodificação com a classe utilitária Base64
        * para byte e depois convertemos para String
        * */

        String[] credentials = authString.split(":");

        /*
         * Porém, a autenticação está como user:password
         * sendo necessário realizar o split
         * */

        filterChain.doFilter(request, response);
    }
}
/*@Component
public class FilterTaskAuth implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        *//*
 *
 * Toda requisição, antes de passar pela rota, será filtrado
 * e executará alguma ação se tiver permissão
 * após autenticação através do Basic Auth
 *
 * *//*
        System.out.println();
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}*/
