<%@ page import="java.util.*, src.main.entity.User" %>
<h1> Liste des tudiants</h1>



<table>
    <tr>
          <td>NOM</td>
          <td>PRENOM</td>
          <td>AGE</td>
    </tr>
    <%
        List<User> list = (List<User>) request.getAttribute("listUser");
        for(User user: list){
    %>
        <tr>
           <td><%= user.getNom() %></td>
           <td><%= user.getPrenom() %></td>
           <td><%= user.getAge() %></td>
           <td>
            <a href="user?action=delete&&id=<%= user.getId() %>">Supprimer</a>
            </td>
        </tr>
    <% } %>
</table>