Cypress.on('fail', (err, runnable) => {
  //debugger
	console.log("Error" + err.toString());
  // we now have access to the err instance
  // and the mocha runnable this failed on
})

describe("Visit App", () => {
	it("visit app", () => {
		cy.visit('/categories', {log: true})
	})
})