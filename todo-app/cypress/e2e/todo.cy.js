// cypress/e2e/todo.cy.js
describe("To-Do List App", () => {
  it("can add a task", () => {
    cy.visit("http://localhost:5173"); // Vite default port
    cy.get("input").type("Cumpara lapte");
    cy.get("button").click();
    cy.contains("Cumpara lapte").should("exist");
  });
});
