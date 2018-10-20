
const createExperience = () => {
    let count = document.getElementsByClassName('experience').length;
    createInput(count + 1, 'text', htmlForExperience);
}

const createInput = (name, type, renderer) => {
    // find div that contains all input rows
    let container = document.getElementById('form-inside');

    // create a row
    let row = document.createElement('div');
    row.className = 'form-group row';

    // create input with label
    row.innerHTML = renderer(name, type);
    container.appendChild(row);
}
const htmlForExperience = (number) => {
           return `
                    <div class="col-5"></div>
                    <div class="col-3">
                    <input type="text" class="form-control" id="experience_${number}" placeholder="Company">
                    </div>
                    <div class="col-2">
                    <input type="text" class="form-control" id="time" placeholder="Time period">
                    </div>
                    <div class="col-2"></div>
                    
                  `;
}

const charsLeft = () => document.getElementById('count').innerHTML = "Characters left: " + (160 - document.getElementById('comment').value.length);


