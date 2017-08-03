<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1><u>Insert Jalur Bis</u></h1>
        <form action="InsertJalur" method="POST">
            <table>
                <tr>
                    <td>Kode Jalur</td>
                    <td>:</td>
                    <td><input type="text" name="kodeJalur" /></td>
                </tr>
                <tr>
                    <td>Kota Asal</td>
                    <td>:</td>
                    <td><input type="text" name="kotaAsal" /></td>
                </tr>
                <tr>
                    <td>Kota Tujuan</td>
                    <td>:</td>
                    <td><input type="text" name="kotaTujuan" /></td>
                </tr>
                <tr>
                    <td>Tanggal</td>
                    <td>:</td>
                    <td><input type="text" name="tanggal" /></td>
                </tr>
                <tr>
                    <td>Kursi</td>
                    <td>:</td>
                    <td><input type="text" name="kursi" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td align="right"><input type="submit" value="SIMPAN" /></td>
                </tr>
            </table>
        </form>
        
        <h1><u>Data Jalur Bis</u></h1>
        <table border="1" cellpadding="5">
            <thead>
            <th>No.</th>
            <th>Kode</th>
            <th>Kota Asal</th>
            <th>Kota Tujuan</th>
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
                        java.util.List<service.ModelJalur> result = port.getAllJalur();
                        for (int i=0; i<result.size(); i++) {
                            out.println("<tr>");
                            out.println("<td>" + (i + 1) + "</td>");
                            out.println("<td>" + result.get(i).getKodeJalur()+ "</td>");
                            out.println("<td>" + result.get(i).getKotaAsal()+ "</td>");
                            out.println("<td>" + result.get(i).getKotaTujuan()+ "</td>");
                            out.println("<td>" + result.get(i).getTanggal()+ "</td>");
                            out.println("<td>" + result.get(i).getKursi()+ "</td>");
                            
                            out.println("<td><a href='DeleteJalur?kodeJalur=" + result.get(i).getKodeJalur()+ "'>hapus</a></td>");
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
