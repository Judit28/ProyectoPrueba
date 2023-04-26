<%@page import="com.google.gson.Gson"%>
<%@page import="dao.ClienteDAO"%>
<%@page import="java.util.List"%>
<%@page import="dto.Cliente"%>

<%
    String op = request.getParameter("op");
    switch (op) {
        case "1":
            Gson g = new Gson();
            List<Cliente> lista = ClienteDAO.listar();
            String resultado = g.toJson(lista);
            resultado = "{\"data\":" + resultado + "}";
            out.print(resultado);
            break;
        case "2":
            String codi = request.getParameter("codi");
            String pate = request.getParameter("pate");
            String mate = request.getParameter("mate");
            String nomb = request.getParameter("nomb");

            Cliente c = new Cliente(codi, pate, mate, nomb);
            boolean b = ClienteDAO.agregar(c);
            if (b) {
                out.print("{\"resultado\":\"ok\"}");
            } else {
                out.print("{\"resultado\":\"error\"}");
            }
            break;

        case "4":
            codi = request.getParameter("codi");
            ClienteDAO.eliminar(codi);
            out.print("{\"resultado\":\"ok\"}");
            break;
    }


%>
