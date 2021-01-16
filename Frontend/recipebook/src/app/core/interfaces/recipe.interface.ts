
import { Keyword } from '@core/interfaces/keyword.interface';

export interface Recipe {
    id: number;
    title: string;
    ingredients: string;
    instructions: string;
    category: 'SOUP' | 'MAINCOURSE' | 'DESSERT';
    status: 'PUBLIC'  | 'PRIVATE';
    keywords: Keyword[];
    image: string;
    author?: number;
    folder?:string;

}

