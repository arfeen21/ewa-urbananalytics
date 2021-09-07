import { transition, trigger, query, style, animateChild, group, animate } from '@angular/animations';

export const slideInAnimation =
    trigger('routeAnimations', [
        // Top Bottom
        transition('Groups => Dashboard, Groups => Datasets, Datasets => Dashboard', [
            style({ position: 'relative' }),
            query(':enter, :leave', [
                style({
                    position: 'absolute',
                    height: '100%',
                    top: 0,
                    left: 0,
                    width: '100%'
                })
            ]),
            query(':enter', [
                style({ top: '-100%' })
            ]),
            query(':leave', animateChild()),
            group([
                query(':leave', [
                    animate('400ms ease-in-out', style({ top: '100%' }))
                ]),
                query(':enter', [
                    animate('400ms ease-in-out', style({ top: '0%' }))
                ])
            ]),
            query(':enter', animateChild()),
        ]),
        // Bottom Top
        transition('Dashboard => Groups, Dashboard => Datasets, Datasets => Groups', [
            style({ position: 'relative' }),
            query(':enter, :leave', [
                style({
                    position: 'absolute',
                    height: '100%',
                    bottom: 0,
                    left: 0,
                    width: '100%'
                })
            ]),
            query(':enter', [
                style({ bottom: '-100%' })
            ]),
            query(':leave', animateChild()),
            group([
                query(':leave', [
                    animate('400ms ease-in-out', style({ bottom: '100%' }))
                ]),
                query(':enter', [
                    animate('400ms ease-in-out', style({ bottom: '0%' }))
                ])
            ]),
            query(':enter', animateChild()),
        ]),

        // Left Right
        transition('Datasets => DatasetsSingle,  Dashboard => Datasets, Dashboard => DatasetsSingle , Datasets => Upload, Upload => Graphs, GroupsSingle => DatasetsSingle , Groups => GroupsSingle', [
            style({ position: 'relative' }),
            query(':enter, :leave', [
                style({
                    position: 'absolute',
                    height: '100%',
                    top: 0,
                    right: 0,
                    width: '100%'
                })
            ]),
            query(':enter', [
                style({ right: '-100%' })
            ]),
            query(':leave', animateChild()),
            group([
                query(':leave', [
                    animate('400ms ease-in-out', style({ right: '100%' }))
                ]),
                query(':enter', [
                    animate('400ms ease-in-out', style({ right: '0%' }))
                ])
            ]),
            query(':enter', animateChild()),
        ]),
        //Right Left
        transition('DatasetsSingle => *', [
            style({ position: 'relative' }),
            query(':enter, :leave', [
                style({
                    position: 'absolute',
                    height: '100%',
                    top: 0,
                    left: 0,
                    width: '100%'
                })
            ]),
            query(':enter', [
                style({ left: '-100%' })
            ]),
            query(':leave', animateChild()),
            group([
                query(':leave', [
                    animate('400ms ease-in-out', style({ left: '100%' }))
                ]),
                query(':enter', [
                    animate('400ms ease-in-out', style({ left: '0%' }))
                ])
            ]),
            query(':enter', animateChild()),
        ])
    ]);