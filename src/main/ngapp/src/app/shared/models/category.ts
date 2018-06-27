export class Category {
    constructor(
    	public id: number,
    	public name: string,
    	public description: string,
		public testCount: number = 0,
		public hostCount: number = 0) {

    }

}
