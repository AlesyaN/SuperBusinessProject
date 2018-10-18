
const createExperience = () => {
    let count = document.getElementsByClassName('experience').length;
    createInput(count + 1, 'text', htmlForExperience);
}

let inputFields = new Map([
    ['Email', 'text'],
    ['Username', 'text'],
    ['Firstname', 'text'],
    ['Lastname', 'text'],
    ['Birthdate', 'text'],
    ['Birthplace', 'text'],
    ['Edu', 'text'],
    ['Scope', 'text'], 
    ['Company', 'text'],
    ['Position', 'text'],
    ['Password', 'password'],
    ['Repeat password', 'password'],       
]);

//now what to do with it 
let fieldsWithRequired = ['Email', 'Username', 'Password', 'Repeat password'];

// now we can delete all fields in sighup html


const createInput = (name, type, renderer) => {
    // find div that contains all input rows
    let container = document.getElementById('inputs_container');

    // create a row
    let row = document.createElement('div');
    row.setAttribute('class', 'row');

    // create input with label
    row.innerHTML = renderer(name, type, fieldsWithRequired.includes(name));
    container.appendChild(row);
}

const htmlForExperience = (number) => {
    return `<div class="col">
            </div>
            <div class="col">
                <input class="experience" id="experience_${number}" name="experience_${number}" placeholder="Enter Company Name">
                <input class="time" id="time_${number}" name="time_${number}" placeholder="Enter Time Period">
            </div>`;
}

const htmlForInput = (name, type, required) => {
    let id = `${name}_input`;
    if (name == 'Repeat password') {
        return `<div class="col">
                <label for="${id}"><b>${name} ${required ? '*' : ''}</b></label>
            </div>
            <div class="col">
                <input id="${id}" type="${type}" placeholder="Repeat Password" name="${name}" ${required ? 'required' : ''}>
            </div>`;
    }
    return `<div class="col">
                <label for="${id}"><b>${name} ${required ? '*' : ''}</b></label>
            </div>
            <div class="col">
                <input id="${id}" type="${type}" placeholder="Enter ${name}" name="${name}" ${required ? 'required' : ''}>
            </div>`;
}

