<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<h1> Page d ajout</h1>

<form action="user?action=save" method="POST">

        <label>Nom </label>
        <input type="text" class="form-control" name="nom">
        <br>

         <label>Prenom </label>
         <input type="text" class="form-control" name="prenom">
         <br>

         <label>Age </label>
         <input type="number" class="form-control" name="age">
         <br>

          <label>Roles </label>
          <select name="role" class="form-control">
                 <c:forEach items="${roles}" var="role" >
                        <option value="${role.id}"> ${role.name}  </option>
                  </c:forEach>
          <select>


         <button class="btn btn-info" name="save" type="submit">Save</button>
         <button class="btn btn-danger" name="save" type="reset">Retour</button>
</form>
