<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/11/2023
  Time: 1:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>

<script>
const socket = new WebSocket("ws://localhost:8080/observer");
socket.addEventListener("message", (event) => {

    let data = JSON.parse(event.data);

    let idUser = data.idUser;
    let type = data.type;

            if(idUser===${sessionScope.user.id}){
            if(type==="updateRole"){
            $.ajax({
                url : ${pageContext.request.contextPath}"/anime-main/NotifyAccount",
                type : "post",
                data : {
                    idUser : idUser,
                },
                success : function(data) {
                    console.log(data);

                },
                error : function(data) {
                    console.log(data)
                }
            });
            }
            if(type==="removeUser"){
                $.ajax({
                    url : ${pageContext.request.contextPath}"/anime-main/LogOutAccount",
                    type : "post",
                    data : {
                        idUser : idUser,
                    },
                    success : function(data) {

                            window.location.href='${pageContext.request.contextPath}/anime-main/Index'


                    },
                    error : function(data) {
                        console.log(data)
                    }
                });
            }
        }



  });
</script>
