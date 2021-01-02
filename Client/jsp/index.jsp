<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>


<html>
<head>
<link rel="stylesheet" href="style.css">
</head>

<body >
  <div class="bodywrapper">

  <form method="GET" action="home">

    <div class="center">
      <div>
        <input class="inputs" type="text" name="keyword" size=20>
        <input class="inputs" type="number" name="number"  min="1" max="100">
      </div>

      <div>
        <button class="buttons" type="submit" name="btn" value="search"> Search</button>
        <button class="buttons" type="submit" name="btn" value="random"> Random</button>
      </div>
    </div>

  </form>


  <!-- FOR ONE QUOTE -->
  <% if(request.getAttribute("quotes") != null){ %>
    <%  ArrayList<JSONObject> quotes = (ArrayList) request.getAttribute("quotes");
        for(int i = 0; i< quotes.size(); i++){
          String quote = quotes.get(i).get("Quote").toString();
          String author = quotes.get(i).get("Author").toString();
    %>
          <div class="quotestyle">
            <a><%= quote %> - <%= author %></a>
          </div>


    <%} %>


    <%  ArrayList<JSONObject> movies = (ArrayList) request.getAttribute("movies");
        ArrayList<String> posters = (ArrayList) request.getAttribute("posters");
        for(int i = 0; i< movies.size(); i++){
          String title = movies.get(i).get("Title").toString();
          String overview = movies.get(i).get("Overview").toString();
          String poster = posters.get(i);
    %>
          <div class="a">

            <!-- IMAGE -->
            <img class="b" src="<%= poster %>"/>

            <!-- DESCRIPTION -->
            <div class="c">
              <div class="t">
                <%= title %>
              </div>
                <%= overview %>
              </div>
          </div>

    <%} %>
  <% } %>
  </div>
</body>
</html>
