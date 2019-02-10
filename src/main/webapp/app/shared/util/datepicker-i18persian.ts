import { Injectable } from '@angular/core';
import { NgbDateStruct, NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';

const WEEKDAYS_SHORT = ['د', 'س', 'چ', 'پ', 'ج', 'ش', 'ی'];
const MONTHS = ['فروردین', 'اردیبهشت', 'خرداد', 'تیر', 'مرداد', 'شهریور', 'مهر', 'آبان', 'آذر', 'دی', 'بهمن', 'اسفند'];

@Injectable()
export class NgbDatepickerI18nPersian extends NgbDatepickerI18n {
    getWeekdayShortName(weekday: number) {
        return WEEKDAYS_SHORT[weekday - 1];
    }
    getMonthShortName(month: number) {
        return MONTHS[month - 1];
    }
    getMonthFullName(month: number) {
        return MONTHS[month - 1];
    }
    getDayAriaLabel(date: NgbDateStruct): string {
        return `${date.year}-${this.getMonthFullName(date.month)}-${date.day}`;
    }
}

// import {Component, Injectable} from '@angular/core';
// import {NgbDatepickerI18n, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';

// const I18N_VALUES = {
//     'fa': {
//         weekdays: ['د', 'س', 'چ', 'پ', 'ج', 'ش', 'ی'],
//         months: ['فروردین', 'اردیبهشت', 'خرداد', 'تیر', 'مرداد', 'شهریور', 'مهر', 'آبان', 'آذر', 'دی', 'بهمن', 'اسفند'],
//     }
//     // other languages you would support
// };
//
// // Define a service holding the language. You probably already have one if your app is i18ned. Or you could also
// // use the Angular LOCALE_ID value
// @Injectable()
// export class I18n {
//     language = 'fa';
// }
//
// // Define custom service providing the months and weekdays translations
// @Injectable()
// export class NgbDatepickerI18nPersian extends NgbDatepickerI18n {
//
//     constructor(private _i18n: I18n) {
//         super();
//     }
//
//     getWeekdayShortName(weekday: number): string {
//         return I18N_VALUES[this._i18n.language].weekdays[weekday - 1];
//     }
//     getMonthShortName(month: number): string {
//         return I18N_VALUES[this._i18n.language].months[month - 1];
//     }
//     getMonthFullName(month: number): string {
//         return this.getMonthShortName(month);
//     }
//
//     getDayAriaLabel(date: NgbDateStruct): string {
//         return `${date.day}-${date.month}-${date.year}`;
//     }
// }
