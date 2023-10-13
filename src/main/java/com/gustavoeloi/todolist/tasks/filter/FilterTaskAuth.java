package com.gustavoeloi.todolist.tasks.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.gustavoeloi.todolist.users.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

import static java.util.Base64.getDecoder;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {
    @Autowired
    UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if(servletPath.equals("/task/")){
            // pegar a autenticação do usuário
            var authorization = request.getHeader("Authorization");
            var authEncode = authorization.substring("Basic".length()).trim();
            byte[] authDecode = Base64.getDecoder().decode(authEncode);
            var authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String userName = credentials[0];
            String password = credentials[1];

            // validar o usuário
            var user = this.userRepository.findByUserName(userName);
            if(user == null){
                response.sendError(401);
            } else {
                // validar a senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified){
                    // continuar a requisição/ resposta
                    request.setAttribute("userId", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
