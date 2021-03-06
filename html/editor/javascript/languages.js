var indices = [
    'slides',
    'tags',
    'themes',
    'title',
    'text',
    'picture',
    'browse',
    'selected',
    'not selected',
    'theme',
    'select theme',
    'cancel',
    'submit',
    'delete',
    'upload image',
    'add slide',
    'add tag',
    'add theme',
    'tag',
    'date time',
    'from',
    'to',
    'override other tags',
    'no',
    'yes',
    'theme name',
    'css',
    'theme description',
    'add day',
    'day',
    'days',
    'time',
    'close',
    'add',
    'mon',
    'tue',
    'wed',
    'thu',
    'fri',
    'sat',
    'sun'
];

var nb_NO = [
    'Slides',
    'Tags',
    'Temaer',
    'Tittel',
    'Tekst',
    'Bilde',
    'Velg Fil',
    'Valgt',
    'Ikke Valgt',
    'Tema',
    'Velg Tema',
    'Avbryt',
    'Lagre',
    'Slett',
    'Velg et bilde',
    'Legg til Slide',
    'Legg til Tag',
    'Legg til Tema',
    'Tag',
    'Dato / Tid',
    'Fra',
    'Til',
    'Overskriv andre tags',
    'Nei',
    'Ja',
    'Temanavn',
    'CSS',
    'Beskrivelse av Tema',
    'Legg til Dag',
    'Dag',
    'Dager',
    'Tid',
    'Lukk',
    'Legg til',
    'Mandag',
    'Tirsdag',
    'Onsdag',
    'Torsdag',
    'Fredag',
    'Lørdag',
    'Søndag'
];

var en_US = [
    'Slides',
    'Tags',
    'Themes',
    'Title',
    'Text',
    'Picture',
    'Browse',
    'Selected',
    'Not Selected',
    'Theme',
    'Select Theme',
    'Cancel',
    'Submit',
    'Delete',
    'Please upload a picture',
    'Add Slide',
    'Add Tag',
    'Add Theme',
    'Tag',
    'Date / Time',
    'From',
    'To',
    'Override other Tags',
    'No',
    'Yes',
    'Theme Name',
    'CSS',
    'Theme Description',
    'Add Day',
    'Day',
    'Days',
    'Time',
    'Close',
    'Add',
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday',
    'Sunday'
];

var lang = [];

function print(key){
    document.write(getTranslation(key));
}

function getTranslation(key){
    if(key == 'org') return 'Salem Menighet';
    var i = indices.indexOf(key);
    var value = lang[i];
    if(value == "" || value == undefined) value = key;
    return value;
}