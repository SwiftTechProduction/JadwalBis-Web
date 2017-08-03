<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1><u>Insert Tiket</u></h1>
        <form action="InsertTiket" method="POST">
            <table>
                <tr>
                    <td>Kode</td>
                    <td>:</td>
                    <td><input type="text" name="kode" /></td>
                </tr>
                <tr>
                    <td>Jalur</td>
                    <td>:</td>
                    <td><input type="text" name="jalur" /></td>
                </tr>
                <tr>
                    <td>No Kursi</td>
                    <td>:</td>
                    <td><input type="text" name="noKursi" /></td>
                </tr>
                <tr>
                    <td>Jam Berangkat</td>
                    <td>:</td>
                    <td><input type="text" name="jam" /></td>
                </tr>
                <tr>
                    <td>Harga</td>
                    <td>:</td>
                    <td><input type="text" name="harga" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td align="right"><input type="submit" value="SIMPAN" /></td>
                </tr>
            </table>
        </form>
        
        <h1><u>Data Tiket</u></h1>
        <table border="1" cellpadding="5">
            <thead>
            <th>No.</th>
            <th>Kode</th>
            <th>Jalur</th>
            <th>No Kursi</th>
            <th>Jam Berangkat</th>
            <th>Harga</th>
            <th>Hapus</th>
            </thead>
            <tbody>
                <%-- start web service invocation --%>
                <%
                    try {
                        service.BisService_Service service= new service.BisService_Service();
                        service.BisService port = service.getBisServicePort();
                        java.util.List<service.ModelTiket> result = port.getAllTiket();
                        for (int i=0; i<result.size(); i++) {
                            out.println("<tr>");
                            out.println("<td>" + (i + 1) + "</td>");
                            out.println("<td>" + result.get(i).getKode()+ "</td>");
                            out.println("<td>" + result.get(i).getJalur()+ "</td>");
                            out.println("<td>" + result.get(i).getNoKursi()+ "</td>");
                            out.println("<td>" + result.get(i).getJamBerangkat()+ "</td>");
                            out.println("<td>" + result.get(i).getHarga()+ "</td>");
                            
                            out.println("<td><a href='DeleteTiket?kode=" + result.get(i).getKode()+ "'>hapus</a></td>");
                            out.println("</tr>");
                        }
                    } catch (Exception ex) {
                        out.println("Terjadi kesalahan");
                    }
                %>
                <%-- end web service invocation --%>
            </tbody>
        </table>
    </center>
</body>
</html>
