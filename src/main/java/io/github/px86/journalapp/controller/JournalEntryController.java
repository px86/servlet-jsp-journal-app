package io.github.px86.journalapp.controller;

import io.github.px86.journalapp.model.JournalEntry;
import io.github.px86.journalapp.service.JournalEntryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/journal/*")
public class JournalEntryController extends HttpServlet {

  private static boolean match(String pattern, String url) {
    Pattern patternObj = Pattern.compile(pattern);
    Matcher matcher = patternObj.matcher(url);
    return matcher.find();
  }

  private static void renderError(
      HttpServletRequest request, HttpServletResponse response, String errorMessage, int statusCode)
      throws ServletException, IOException {
    request.setAttribute("errorMessage", errorMessage);
    response.setStatus(statusCode);
    request.getRequestDispatcher("/error.jsp").forward(request, response);
  }

  private static void render404(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    renderError(request, response, "resource not found!", HttpServletResponse.SC_NOT_FOUND);
  }

  private static String getRequestURIWithoutContextPath(HttpServletRequest request) {
    return request.getRequestURI().replaceFirst(("^" + request.getContextPath()), "");
  }

  private JournalEntryService journalEntryService;

  public JournalEntryController() throws Exception {
    this.journalEntryService = new JournalEntryService();
  }

  // @Override
  // public void service(HttpServletRequest request, HttpServletResponse response)
  //     throws ServletException, IOException {
  //   doGet(request, response);
  // }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String path = getRequestURIWithoutContextPath(request);

    if (match("^/journal/?$", path)) {
      request.setAttribute("journalEntries", this.journalEntryService.findAll());
      request.getRequestDispatcher("/homepage.jsp").forward(request, response);
    } else if (match("^/journal/[0-9]+$", path)) {
      Long id = Long.parseLong(path.replaceFirst("^/journal/", ""));

      Optional<JournalEntry> journalEntryOpt = this.journalEntryService.findById(id);
      if (journalEntryOpt.isPresent()) {
        JournalEntry journalEntry = journalEntryOpt.get();
        request.setAttribute("journalEntry", journalEntry);
        request.getRequestDispatcher("/entry.jsp").forward(request, response);
      } else {
        JournalEntryController.render404(request, response);
      }

    } else if (match("^/journal/new$", path)) {
      request.getRequestDispatcher("/entry-new.jsp").forward(request, response);

    } else if (match("^/journal/[0-9]+/delete$", path)) {

      Long id = Long.parseLong(path.replaceFirst("^/journal/", "").replaceFirst("/delete$", ""));
      this.journalEntryService.deleteById(id);
      response.sendRedirect(request.getContextPath() + "/journal");

    } else if (match("^/journal/[0-9]+/edit$", path)) {

      Long id = Long.parseLong(path.replaceFirst("^/journal/", "").replaceFirst("/edit", ""));
      Optional<JournalEntry> journalEntry = this.journalEntryService.findById(id);
      if (journalEntry.isPresent()) {
        request.setAttribute("journalEntry", journalEntry.get());
        request.getRequestDispatcher("/entry-edit.jsp").forward(request, response);
      } else {
        JournalEntryController.render404(request, response);
      }

    } else {
      JournalEntryController.render404(request, response);
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setAttribute("baseURL", "/journal");
    String path = getRequestURIWithoutContextPath(request);

    // Create new
    if (match("^/journal/?$", path)) {

      JournalEntry journalEntry = new JournalEntry();
      journalEntry.setTitle(request.getParameter("title"));
      journalEntry.setBody(request.getParameter("body"));
      journalEntry.setLastModified(JournalEntry.timeStringOf(new Date()));

      this.journalEntryService.save(journalEntry);
      response.sendRedirect(request.getContextPath() + "/journal");
    }
    // Update existing
    else if (match("^/journal/[0-9]+$", path)) {
      Long id = Long.parseLong(path.replaceFirst("^/journal/", ""));

      JournalEntry journalEntry = new JournalEntry();
      journalEntry.setId(id);
      journalEntry.setTitle(request.getParameter("title"));
      journalEntry.setBody(request.getParameter("body"));
      journalEntry.setLastModified(JournalEntry.timeStringOf(new Date()));

      this.journalEntryService.update(journalEntry);
      response.sendRedirect(request.getContextPath() + "/journal");
    }
  }

}
