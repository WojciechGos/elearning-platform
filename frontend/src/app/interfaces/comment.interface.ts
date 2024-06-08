export interface Comment {
    id?: number;
    content: string;
    authorFirstName: string;
    authorLastName: string;
    createdDate: string;
    isCourseAuthor: boolean;
}