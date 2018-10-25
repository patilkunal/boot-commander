Cypress.on('fail', (err, runnable) => {
  //debugger
	console.log(err);
  // we now have access to the err instance
  // and the mocha runnable this failed on
})

describe("Visit App", () => {
	it("visit app", () => {
		cy.visit('http://localhost:4200', {log: true})
	})
})