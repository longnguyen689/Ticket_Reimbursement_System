package main.utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import main.controller.EmployeeTicketController;
import main.controller.LoginController;
import main.controller.ManagerTicketController;
import main.controller.SubmitTicketController;
import main.controller.EmployeeTicketController;

public class RequestHelper {
    private static Gson gson = new Gson();

    public static String process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("1" + req.getRequestURI());
        switch (req.getRequestURI()) {
            case "/TicketReimbursementSystem_war/validate.do":
                return LoginController.login(req, resp); //using

            case "/TicketReimbursementSystem_war/tickets.employee.view":
                return "view.tickets.employee.do"; //using

            case "/TicketReimbursementSystem_war/create-new-request.do":
                return "create-ticket.html"; //returned by controller, using


            case "/TicketReimbursementSystem_war/submit.do":
                return SubmitTicketController.submit(req); //using

            case "/TicketReimbursementSystem_war/approve-deny.do":
                return "pending-ticket.do";

//            case "/TicketReimbursementSystem_war/goHome.do":
//                return LoginController.goHome(req);

            case "/TicketReimbursementSystem_war/resolve.do":
                return ManagerTicketController.resolve(req);

            case "/TicketReimbursementSystem_war/logout.do":
                req.getSession().invalidate();
                return "login.html";

            default:
                return "login.html";
        }
    }

    public static void process_2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("2" + req.getRequestURI());
        EmployeeTicketController controllerUser = new EmployeeTicketController();
        ManagerTicketController managerController = new ManagerTicketController();

        switch ( req.getRequestURI() ) {
            case "/TicketReimbursementSystem_war/tickets-employee.view":
                controllerUser.getTicketsForEmployee(req, resp);
                break;

            case "/TicketReimbursementSystem_war/tickets-manager.view":
                managerController.managerViewsTickets(req, resp);
                break;

            case "/TicketReimbursementSystem_war/getResolved.view":
                managerController.getResolvedTickets(req, resp);
                break;

//            case "/ExpenseReimbursementSystem/getPending.change":
//                controllerUser.getTicketsForManager_Controller(req, resp); break;

        }
    }

}